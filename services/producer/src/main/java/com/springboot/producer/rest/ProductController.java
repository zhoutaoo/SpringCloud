package com.springboot.producer.rest;

import com.springboot.cloud.core.entity.Result;
import com.springboot.producer.entity.Product;
import com.springboot.producer.entity.form.ProductAddForm;
import com.springboot.producer.entity.form.ProductQueryForm;
import com.springboot.producer.entity.form.ProductUpdateForm;
import com.springboot.producer.entity.param.ProductQueryParam;
import com.springboot.producer.service.IProductService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/products")
@Api("product")
@Slf4j
public class ProductController {

    @Autowired
    private IProductService productService;

    @ApiOperation(value = "新增产品", notes = "新增一个产品")
    @ApiImplicitParam(name = "productAddForm", value = "新增产品form表单", required = true, dataType = "ProductAddForm")
    @RequestMapping(method = RequestMethod.POST)
    public Result<Long> add(@Valid @RequestBody ProductAddForm productAddForm) {
        log.info("name:", productAddForm);
        Product product = new Product(productAddForm.getName(), productAddForm.getDescription());
        product.setCreatedBy("system");
        product.setUpdatedBy("system");
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
            @ApiImplicitParam(name = "productUpdateForm", value = "产品实体", required = true, dataType = "ProductUpdateForm")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable long id, @Valid @RequestBody ProductUpdateForm productUpdateForm) {
        Product product = new Product();
        product.setId(id);
        product.setName(productUpdateForm.getName());
        productService.update(product);
        return Result.success();
    }

    @ApiOperation(value = "获取产品", notes = "获取指定产品信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "产品ID", required = true, dataType = "long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Product> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        return Result.success(productService.get(id));
    }

    @ApiOperation(value = "查询产品", notes = "根据条件查询产品信息，简单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "产品名称", required = true, dataType = "string")
    })
    @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<List<Product>> query(@RequestParam String name) {
        log.info("query with name:{}", name);
        return Result.success(productService.query(new ProductQueryParam(name)));
    }

    @ApiOperation(value = "搜索产品", notes = "根据条件查询产品信息")
    @ApiImplicitParam(name = "productQueryForm", value = "产品查询参数", required = true, dataType = "ProductQueryForm")
    @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Result<List<Product>> search(@Valid @RequestBody ProductQueryForm productQueryForm) {
        log.info("search with productQueryForm:", productQueryForm);
        return Result.success(productService.query(new ProductQueryParam(productQueryForm.getName())));
    }
}

