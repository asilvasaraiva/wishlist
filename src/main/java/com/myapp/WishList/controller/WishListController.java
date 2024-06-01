package com.myapp.WishList.controller;

import com.myapp.WishList.entity.Product;
import com.myapp.WishList.entity.RequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/wishlist")
@Tag(name = "WishList", description = "Lista de Desejos")
public interface WishListController {

    @Operation(
            summary = "Adiciona um produto na lista de desejos do cliente",
            description = "Ao utilizar essa rota caso o cliente não exista o mesmo é criado no banco e o " +
                    "produto selecionado inserido, caso o cliente ja possua um produto ou a lista esteja cheia" +
                    " retornará erro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao adicionar o produto na lista do cliente."),
            @ApiResponse(responseCode = "208", description = "Quando o produto informado ja existir na lista do cliente."),
            @ApiResponse(responseCode = "403", description = "Quando a lista do cliente ja estiver cheia.")
    })
    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addProductToClient(@RequestBody RequestDTO req);

    @Operation(
            summary = "Retorna a lista de desejos do cliente",
            description = "Ao chamar essa rota é retornado a lista de produtos desejadas pelo cliente ou " +
                    "vazio quando não houver.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao retornar na lista de produtos do cliente")})
    @GetMapping(value = "/person/{loginID}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Product>> getWishList(@PathVariable String loginID);

    @Operation(
            summary = "Verifica se um determinado produto ja existe na lista de desejos do cliente",
            description = "Ao chamar essa rota é retornado verdadeiro ou falso caso o produto informado ja exista" +
                    "na lista de desejos do cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso verificar a presença ou nao do produto na lista " +
                    "de desejos do cliente.")})
    @GetMapping(value = "/person/{loginID}/search/product/{codProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> checkProductExist(@PathVariable String loginID, @PathVariable Long codProduct );

    @Operation(
            summary = "Realiza a remoção de um produto da lista de desejos do cliente",
            description = "Ao chamar essa rota o sistema verifica a presença do produto informado na lista do cliente " +
                    "e realiza a remoção caso exista, caso não é retornado um erro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso verificar a presença ou nao do produto na lista " +
                    "do cliente."),
            @ApiResponse(responseCode = "404", description = "Quando o produto informado não estiver presente na lista" +
                    " de desejos do cliente.")})
    @DeleteMapping(value = "/person/{loginID}/product/{codProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> removeProduct(@PathVariable String loginID, @PathVariable Long codProduct );
}
