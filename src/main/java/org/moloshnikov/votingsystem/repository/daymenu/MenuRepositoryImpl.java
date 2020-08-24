package org.moloshnikov.votingsystem.repository.daymenu;

import org.moloshnikov.votingsystem.model.Menu;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    private final CrudMenuRepository crudMenuRepository;

    public MenuRepositoryImpl(CrudMenuRepository crudMenuRepository) {
        this.crudMenuRepository = crudMenuRepository;
    }

    @Override
    public Menu save(Menu menu) {
        return crudMenuRepository.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return crudMenuRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return crudMenuRepository.get(id);
    }

    @Override
    public Set<Menu> getAll() {
        return crudMenuRepository.getAll();
    }

    @Override
    public Set<Menu> getAllByDate(LocalDate localDate) {
        return crudMenuRepository.getAllByDate(localDate);
    }


}
