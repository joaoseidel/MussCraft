package io.seidel.musscraft.services.impl;

import io.seidel.musscraft.database.models.Home;
import io.seidel.musscraft.services.api.HomeService;
import io.seidel.musscraft.services.exceptions.HomeNotFoundException;
import io.seidel.musscraft.services.vo.HomeCreateVO;
import io.seidel.musscraft.services.vo.HomeSearchVO;

import java.util.Collection;

public class HomeServiceImpl implements HomeService {

    @Override
    public void create(HomeCreateVO create) {

    }

    @Override
    public Home find(HomeSearchVO search) throws HomeNotFoundException {
        return null;
    }

    @Override
    public Collection<Home> find(HomeSearchVO search, boolean hasNext) {
        return null;
    }
}
