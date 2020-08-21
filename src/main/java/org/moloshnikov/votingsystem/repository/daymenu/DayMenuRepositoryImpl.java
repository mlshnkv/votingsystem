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
        return crudMenuRepository.save(dayMenu);
    }

    @Override
    public boolean delete(int id) {
        return crudMenuRepository.delete(id) != 0;
    }

    @Override
    public DayMenu get(int id) {
        return crudMenuRepository.get(id);
    }

    @Override
    public List<DayMenu> getAll() {
        return crudMenuRepository.getAll();
        //return crudMenuRepository.findAll();
    }

    @Override
    public List<DayMenu> getAllByDate(LocalDate localDate) {
        return crudMenuRepository.getAllByDate(localDate);
    }


}
