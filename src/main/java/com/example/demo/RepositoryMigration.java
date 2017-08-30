package com.example.demo;



import java.util.List;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface RepositoryMigration extends CrudRepository<migration,Integer> {
	

    @Query("SELECT m FROM migration m WHERE m.date=date")
	 List<migration> findBydate(@Param("date")String  date);


}
