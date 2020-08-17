package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.DayMenu;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DayMenuRepositoryImpl implements DayMenuRepository {
    private final CrudMenuRepository crudMenuRepository;

    public DayMenuRepositoryImpl(CrudMenuRepository crudMenuRepository) {
        this.crudMenuRepository = crudMenuRepository;
    }

    @Override
    public DayMenu save(DayMenu dayMenu) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public DayMenu get(int id) {
        return crudMenuRepository.findById(id).orElse(null);
    }

    @Override
    public List<DayMenu> getAll() {
        return null;
    }

    @Override
    public List<DayMenu> getAllByDate(LocalDate localDate) {
        return crudMenuRepository.getAllByDate(localDate);
    }


}
