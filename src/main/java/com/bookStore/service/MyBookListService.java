package com.bookStore.service;

import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {

  @Autowired
  private MyBookRepository myBookRepository;

  public void saveMyBook(MyBookList myBookList) {
    myBookRepository.save(myBookList);
  }

  public List<MyBookList> getAllMyBooks() {
    return myBookRepository.findAll();
  }

  public void deleteById(Integer id) {
    myBookRepository.deleteById(id);
  }
}
