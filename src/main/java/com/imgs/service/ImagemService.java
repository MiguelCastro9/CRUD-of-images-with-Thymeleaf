package com.imgs.service;

import com.imgs.model.Imagem;
import com.imgs.repository.ImagemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel Castro
 */
@Service
public class ImagemService {
    
    @Autowired
    private ImagemRepository imagemRepository;
    
    public Imagem save(Imagem imagem) {
        
        return imagemRepository.saveAndFlush(imagem);
    }
    
    public List<Imagem> list() {
        
        return imagemRepository.findAll();
    }
    
}
