package group.boboaigaowei;

import redis.clients.jedis.Jedis;

public class RedisConnectionTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);

		jedis.set("name", "sylvia");
		String result = jedis.get("name");
		System.out.println("Value of Key : " + result);
		jedis.close();
	}


}
