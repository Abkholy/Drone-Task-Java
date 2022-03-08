/*
 * Copyright (c) 2020. overrideeg.ocm.
 */
package com.musala.dronetask.generic;

import com.musala.dronetask.ui.sys.ResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Abstract base class for REST resources exposing operations on an entity type.
 * All operations will return HTTP status 500 with a plain text body containing an
 * error message if an error occurred during request processing.
 *
 * @param <E> Entity type.
 * @author Abdelrahman Alkholy
 */

@CrossOrigin(
         methods = {RequestMethod.POST,
        RequestMethod.DELETE, RequestMethod.GET,
        RequestMethod.PUT, RequestMethod.OPTIONS, RequestMethod.HEAD},
         allowedHeaders = "*",origins = {"http://localhost:4200","*"})
@RestController
public abstract class ORestController<E extends OEntity> {


    /* Instance variable(s): */
    protected AbstractService<E> mService;


    public ORestController(final AbstractService<E> inService) {
        mService = inService;
    }

    @PostMapping
    public @ResponseBody E postOne(@RequestBody E req)  {
        return mService.save(req);
    }

    @PostMapping("arr")
    public @ResponseBody List<E> postArray(@Valid @RequestBody List<E> inEntity ) {
        return mService.saveArray(inEntity);
    }

    @GetMapping("all")
    public @ResponseBody List<E> getAll() {
        return mService.findAll();
    }

    @GetMapping
    public @ResponseBody Page<E> findAll(
                    @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                    @RequestParam(name = "pageSize", required = false,defaultValue = "100") Integer pageSize) {
        return mService.findAll(page-1, pageSize);
    }

    @GetMapping("/{id}")
    public @ResponseBody E getEntityById(@PathVariable(value = "id") Long inEntityId) {
        return mService.find(inEntityId);
    }


    @PutMapping("/{id}")
    public @ResponseBody E updateEntity(@PathVariable(value = "id") Long inEntityId,@RequestBody final E inEntity)  {
        inEntity.setId(inEntityId);
        return mService.update(inEntity);
    }


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseModel deleteEntityById(@PathVariable(value = "id") Long inEntityId ) {
        return mService.delete(inEntityId);
    }

}
