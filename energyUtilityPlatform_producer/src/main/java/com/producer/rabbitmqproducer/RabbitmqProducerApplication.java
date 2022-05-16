package com.producer.rabbitmqproducer;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@SpringBootApplication
public class RabbitmqProducerApplication {

	@Autowired
	private RabbitTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			String file = "sensor.csv";
			List<Double> data = readDataFromQueue(file);
			for (Double val : data){
			//	UUID sensor_id = UUID.randomUUID();
				UUID sensor_id = UUID.fromString(System.getenv().get("sensor_id"));
				Date timestamp = new Date();
				QueueMessage queueMessage = new QueueMessage(sensor_id,timestamp,val);
				System.out.println(queueMessage);
				template.convertAndSend(RabbitMqConfiguration.exchangeMessage,
						RabbitMqConfiguration.routingKeyMessage, queueMessage);

				TimeUnit.SECONDS.sleep(10);
			}

		};
	}
	static List<Double> readDataFromQueue(String file) {
		List<Double> data = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(file))) {
			List<String[]> all = reader.readAll();
			data = all.stream()
					.map(x -> {
						String str = Arrays.toString(x);
						int size = str.length();
						return Double.parseDouble(str.substring(1, size - 1));
					})
					.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		return data;
	}
}
