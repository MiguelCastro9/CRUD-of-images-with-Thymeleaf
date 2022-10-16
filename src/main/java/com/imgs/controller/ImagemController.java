package com.imgs.controller;

import com.imgs.model.Imagem;
import com.imgs.service.ImagemService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Miguel Castro
 */
@Controller
@RequestMapping("/imagem")
public class ImagemController {
    
    private static String caminho = "C:\\Users\\miguel-home\\Documents\\";
    
    @Autowired
    private ImagemService imagemService;
    
    @GetMapping()
    public ModelAndView cadastro(Imagem imagem) {
        
        ModelAndView mv = new ModelAndView("/cadastro");
        mv.addObject("imagem", imagem);
        mv.addObject("lista", imagemService.list());
        return mv;
    }
    
    @PostMapping("/salvar")
    public String salvar(Imagem imagem, @RequestParam MultipartFile file) {
        
        ModelAndView mv = new ModelAndView("/cadastro");
        imagemService.save(imagem);
        
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                Path caminhoImagem = Paths.get(caminho + String.valueOf(imagem.getId()) + file.getOriginalFilename());
                Files.write(caminhoImagem, bytes);
                imagem.setNome(String.valueOf(imagem.getId()) + file.getOriginalFilename());
                imagemService.save(imagem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "redirect:/imagem";
    }
    
    @ResponseBody
    @GetMapping("/exibeImagem/{imagem}")
    public byte[] exibeImagem(@PathVariable("imagem") String imagem) throws IOException {
        
        File arquivo = new File(caminho + imagem);
        if (imagem != null || imagem.trim().length() > 0) {
            return Files.readAllBytes(arquivo.toPath());
        }
        
        return null;
    }
}
