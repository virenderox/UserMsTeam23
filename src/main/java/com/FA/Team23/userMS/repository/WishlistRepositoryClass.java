package com.FA.Team23.userMS.repository;

import org.springframework.data.repository.CrudRepository;

import com.FA.Team23.userMS.entity.WishlistClass;
import com.FA.Team23.userMS.utility.PrimaryKey;

public interface WishlistRepositoryClass extends CrudRepository<WishlistClass, PrimaryKey> {

}
