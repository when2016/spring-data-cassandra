package com.chin.wang;

import com.chin.wang.cassandra.Person;
import com.chin.wang.cassandra.PersonKey;
import com.chin.wang.cassandra.PersonRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://github.com/lankydan/spring-data-cassandra/blob/master/src/main/java/com/lankydan/Application.java
 * https://lankydanblog.com/2017/12/11/reactive-streams-with-spring-data-cassandra/
 *
 * Created by wanghongen on 2018/4/2
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private PersonRepository personRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Override
  public void run(String... args) throws Exception {
    final PersonKey key = new PersonKey("John", LocalDateTime.now(), UUID.randomUUID());
    final Person p = new Person(key, "Doe", 1000);
    personRepository.insert(p);

    System.out.println("find by first name");
    personRepository.findByKeyFirstName("John").forEach(System.out::println);

    System.out.println("find by first name and date of birth greater than date");
    personRepository
        .findByKeyFirstNameAndKeyDateOfBirthGreaterThan("John", LocalDateTime.now().minusDays(1))
        .forEach(System.out::println);

    System.out.println("find by last name");
    personRepository.findByLastName("Doe").forEach(System.out::println);

  }
}
