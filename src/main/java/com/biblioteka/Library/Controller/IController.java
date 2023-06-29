package com.biblioteka.Library.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IController<T>{
     List<T> getAll();
     T get(@PathVariable Integer id);
     void add(@RequestBody T object);
     void change(@PathVariable Integer id, @RequestBody T object);
     void deleteById(@PathVariable Integer id);

}
