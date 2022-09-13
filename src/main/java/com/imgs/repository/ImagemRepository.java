package com.imgs.repository;

import com.imgs.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Miguel Castro
 */
public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    
}
