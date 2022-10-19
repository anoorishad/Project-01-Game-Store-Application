package com.game_store.Summative1AndrewNoorishadJohnNetzel.repository;

import com.game_store.Summative1AndrewNoorishadJohnNetzel.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TShirtRepository extends JpaRepository<TShirt, Integer> {

    List<TShirt> findByColor(String Color);
    List<TShirt> findBySize(String Size);
}
