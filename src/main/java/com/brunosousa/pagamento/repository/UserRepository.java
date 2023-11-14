package com.brunosousa.pagamento.repository;

import com.brunosousa.pagamento.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.wallet WHERE u.id = (:id)")
    public Optional<User> findByIdAndFetchWalletsEagerly(@Param("id") Long id);
}
