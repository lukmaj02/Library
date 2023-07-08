package com.biblioteka.Library.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller interface which defines CRUD methods.
 * @param <Request> RequestDTO
 * @param <Response> ResponseDTO
 * @param <ID> type of ID corresponded to type of ID in entity in repo
 * */
public interface IController<Request, Response, ID>{
     List<Response> getAll();
     Response getById(@PathVariable ID id);
     void add(@RequestBody Request requestBody);
     void changeById(@PathVariable ID id, @RequestBody Request requestBody);
     void deleteById(@PathVariable ID id);
}
