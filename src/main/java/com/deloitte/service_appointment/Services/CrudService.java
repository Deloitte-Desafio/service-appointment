package com.deloitte.service_appointment.Services;

import java.util.List;

public interface CrudService<ID, T, R> {
    List<T> findAll();
    T findById(ID id);
    T create(R entity);
    T update(ID id, R entity);
    void delete(ID id);
}