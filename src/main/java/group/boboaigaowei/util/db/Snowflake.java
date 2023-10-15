package group.boboaigaowei.util.db;

public class Snowflake {
    // ==============================Fields===========================================
    /**
     * 開始時間戳 (2023-07-21)
     */
    private final long TWEPOCH = 1689868800000L;

    /**
     * 機器id所佔的位數
     */
    private final long WORKER_ID_BITS = 5L;

    /**
     * 數據標示ID所佔位數
     */
    private final long DATACENTER_ID_BITS = 5L;

    /**
     * 計算最大可使用機器ID，結果為31
     */
    private final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 計算最大可用數據中心ID，結果為31
     */
    private final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    /**
     * 序列在id中佔的位數
     */
    private final long SEQUENCE_BITS = 12L;

    /**
     * 機器ID向左移12位(在序列佔位之前)
     */
    private final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 數據標示id向左移17位(12+5 留5位給機器ID)
     */
    private final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 時間戳向左移22位(5+5+12)
     */
    private final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /**
     * 生成序列的掩碼，這裡為4095 (0b111111111111=0xfff=4095)
     */
    private final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /**
     * 工作機器ID(0~31)
     */
    private long workerId;

    /**
     * 數據中心ID(0~31)
     */
    private long datacenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的時間戳
     */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================

    /**
     * 建構函數
     *
     * @param workerId     機器ID (0~31)
     * @param datacenterId 數據中心ID (0~31)
     */
    public Snowflake(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // ==============================Methods==========================================

    /**
     * 獲得下一個ID (同步)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果當前時間小於上一次ID生成的時間戳，說明系統時間回退過，這時候應該拋出異常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一時間生成的，則進行毫秒內序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            //毫秒内序列溢出
            if (sequence == 0) {
                //blocking到下一個毫秒,獲得新的時間戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //時間戳改變，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的時間戳
        lastTimestamp = timestamp;

        //移位並通過或運算拼到一起组成64位的ID
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) // 時間戳左移22位
                | (datacenterId << DATACENTER_ID_SHIFT) // 數據標示左移17位
                | (workerId << WORKER_ID_SHIFT) // 機噐ID左移12位
                | sequence;
    }

    /**
     * blocking到下一個毫秒，直到獲得新的時間戳
     *
     * @param lastTimestamp 上次生成ID的時間戳
     * @return 當前時間戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒為單位的當前時間
     *
     * @return 當前時間(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

}
