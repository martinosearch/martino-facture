package com.martino.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.martino.models.Setting;

@Repository
public interface SettingRepository extends CrudRepository<Setting, String> {

}
