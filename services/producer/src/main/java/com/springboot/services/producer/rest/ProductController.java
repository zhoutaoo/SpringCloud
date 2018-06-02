package com.springboot.services.producer.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.services.producer.entity.form.ProductForm;
import com.springboot.services.producer.entity.form.ProductQueryForm;
import com.springboot.services.producer.entity.param.ProductQueryParam;
import com.springboot.services.producer.entity.po.Product;
import com.springboot.services.producer.service.IProductService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RefreshScope
@RequestMapping("/products")
@Api("product")
@Slf4j
public class ProductController {

    @Autowired
    private IProductService productService;

    @ApiOperation(value = "新增产品", notes = "新增一个产品")
    @ApiImplicitParam(name = "productForm", value = "新增产品form表单", required = true, dataType = "ProductForm")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Result add(@Valid @RequestBody ProductForm productForm) {
        log.info("name:", productForm);
        Product product = productForm.toPo(Product.class);
        return Result.success(productService.add(product));
    }

    @ApiOperation(value = "删除产品", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "产品ID", required = true, dataType = "long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable long id) {
        productService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改产品", notes = "修改指定产品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "productForm", value = "产品实体", required = true, dataType = "ProductForm")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable long id, @Valid @RequestBody ProductForm productForm) {
        Product product = productForm.toPo(Product.class);
        product.setId(id);
        productService.update(product);
        return Result.success();
    }

    @ApiOperation(value = "获取产品", notes = "获取指定产品信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "产品ID", required = true, dataType = "long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result get(@PathVariable long id) {
        log.info("get with id:{}", id);
        return Result.success(productService.get(id));
    }

    @ApiOperation(value = "查询产品", notes = "根据条件查询产品信息，简单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "产品名称", required = true, dataType = "string")
    })
    @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result query(@RequestParam String name) {
        log.info("query with name:{}", name);
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setName(name);
        return Result.success(productService.query(productQueryParam));
    }

    @ApiOperation(value = "搜索产品", notes = "根据条件查询产品信息")
    @ApiImplicitParam(name = "productQueryForm", value = "产品查询参数", required = true, dataType = "ProductQueryForm")
    @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    @RequestMapping(value = "/conditions", method = RequestMethod.POST)
    public Result search(@Valid @RequestBody ProductQueryForm productQueryForm) {
        log.info("search with productQueryForm:", productQueryForm);
        return Result.success(productService.query(productQueryForm.toParam(ProductQueryParam.class)));
    }
}

