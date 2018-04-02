package com.chin.wang.cassandra;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by wanghongen on 2018/4/2
 */
@Repository
public interface PersonRepository extends CassandraRepository<Person, PersonKey> {

  List<Person> findByKeyFirstName(final String firstName);

  List<Person> findByKeyFirstNameAndKeyDateOfBirthGreaterThan(
      final String firstName, final LocalDateTime dateOfBirth);

  @Query(allowFiltering = true)
  List<Person> findByLastName(final String lastName);

}
