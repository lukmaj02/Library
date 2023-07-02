package com.biblioteka.Library.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IController<Request, Response>{
     List<Response> getAll();
     Response getById(@PathVariable Integer id);
     void add(@RequestBody Request requestBody);
     void changeById(@PathVariable Integer id, @RequestBody Request requestBody);
     void deleteById(@PathVariable Integer id);
}
