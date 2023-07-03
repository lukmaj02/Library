package com.biblioteka.Library.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IController<Request, Response, ID>{
     List<Response> getAll();
     Response getById(@PathVariable ID id);
     void add(@RequestBody Request requestBody);
     void changeById(@PathVariable ID id, @RequestBody Request requestBody);
     void deleteById(@PathVariable ID id);
}
