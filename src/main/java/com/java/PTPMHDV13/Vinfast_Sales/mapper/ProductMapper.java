package com.java.PTPMHDV13.Vinfast_Sales.mapper;

import com.java.PTPMHDV13.Vinfast_Sales.dto.request.ProductDTO;
import com.java.PTPMHDV13.Vinfast_Sales.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductDTO productDTO);
}
