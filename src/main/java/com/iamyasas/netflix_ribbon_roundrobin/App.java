package com.iamyasas.netflix_ribbon_roundrobin;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
	
	private static Logger logger = Logger.getAnonymousLogger();
	
	public static void main(String[] args) throws Exception {
		
		String service1 = "service1";
		String service2 = "service2";
		String service3 = "service3";
		
		DownStreamProxy proxy = DownStreamProxy.getInstance();

		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			
			logger.log(Level.INFO, new Date().toString());
			
			int count = sc.nextInt();
			
			if (count == 0) break;

			int[] array = new int[count];
			Arrays.fill(array, 1);

			Arrays.stream(array).parallel()
				.forEach((val) -> {
					System.out.println(proxy.getServer(service1).getHostPort());
//					System.out.println(proxy.getServer(service2).getHostPort());
//					System.out.println(proxy.getServer(service3).getHostPort());
				}
			);
			
			logger.log(Level.INFO, new Date().toString());

		}

		sc.close();

	}
}
