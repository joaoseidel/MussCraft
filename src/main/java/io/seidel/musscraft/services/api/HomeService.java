package io.seidel.musscraft.services.api;

import io.seidel.musscraft.database.models.Home;
import io.seidel.musscraft.services.exceptions.HomeNotFoundException;
import io.seidel.musscraft.services.vo.HomeCreateVO;
import io.seidel.musscraft.services.vo.HomeSearchVO;

import java.util.Collection;

public interface HomeService {
    void create(HomeCreateVO create);

    Home find(HomeSearchVO search) throws HomeNotFoundException;

    Collection<Home> find(HomeSearchVO search, boolean hasNext);
}
