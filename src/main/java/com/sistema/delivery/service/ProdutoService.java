package com.sistema.delivery.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sistema.delivery.domian.Produto;
import com.sistema.delivery.dto.ProdutoDTO;
import com.sistema.delivery.exception.IdNotFound;
import com.sistema.delivery.repository.ProdutoRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private final ModelMapper modelMapper = new ModelMapper();

    public ProdutoDTO findById(Integer id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new IdNotFound( Produto.class.getSimpleName() + " não encontrado"));
        return  modelMapper.map(produto, ProdutoDTO.class);
    }

    public ProdutoDTO create(ProdutoDTO produtoDTO) {
        if (produtoDTO.getId() != null) {
            findById(produtoDTO.getId());
        }
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        return modelMapper.map(repository.save(produto), ProdutoDTO.class);
    }

    public void deleteId(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<ProdutoDTO> findAll() {
        return repository.findAll().stream()
                                .map(produto -> modelMapper.map(produto, ProdutoDTO.class))
                                .collect(Collectors.toList());
    }

    public Page<ProdutoDTO> findAllPage(int page, int size, String direction,String orderBy, String nomeDoProduto, String categoria){
        PageRequest pageRequest;

        pageRequest = ordenarDirecao(page, size, direction, orderBy);

        return verificarPesquisa(nomeDoProduto, categoria, pageRequest);
    }

    private PageRequest ordenarDirecao(int page, int size, String direction, String orderBy) {
        PageRequest pageRequest;
        if (direction.equals("ASC")) {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, orderBy));
        } else {
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, orderBy));
        }
        return pageRequest;
    }

    private Page<ProdutoDTO> verificarPesquisa(String nomeDoProduto, String categoria, PageRequest pageRequest ){

        Page<ProdutoDTO> pageProduto = null;

        if (nomeDoProduto.isEmpty() && categoria.isEmpty()) {
            pageProduto =  repository.findAll(pageRequest).map(produto -> modelMapper.map(produto, ProdutoDTO.class));
        } else if (!nomeDoProduto.isEmpty() && categoria.isEmpty()) {
            pageProduto = repository.findByNomeContainsIgnoreCase(nomeDoProduto, pageRequest).map(produto -> modelMapper.map(produto, ProdutoDTO.class));
        } else if (nomeDoProduto.isEmpty() && !categoria.isEmpty()){
            int idCategoria =Integer.parseInt(categoria);
            pageProduto = repository.findByCategoriasId(idCategoria, pageRequest).map(produto -> modelMapper.map(produto, ProdutoDTO.class));
        } else {
            Integer idCategoria =Integer.valueOf(categoria);
            pageProduto = repository.findByCategoriasIdAndNomeContainsIgnoreCase(idCategoria, nomeDoProduto, pageRequest).map(produto -> modelMapper.map(produto, ProdutoDTO.class));
        }

        return pageProduto;
    }


}
