package com.example.jsonex.productshop;

import com.example.jsonex.productshop.entities.categories.CategoryStatsDTO;
import com.example.jsonex.productshop.entities.categories.XMLCategoryStatsDTO;
import com.example.jsonex.productshop.entities.categories.XMLCategoryStatsList;
import com.example.jsonex.productshop.entities.products.ProductWithoutBuyerDTO;
import com.example.jsonex.productshop.entities.users.UserWithSoldProductsDTO;
import com.example.jsonex.productshop.services.ProductsService;
import com.example.jsonex.productshop.services.SeedService;
import com.example.jsonex.productshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductsService productsService;
    private final UserService userService;

    private final Gson gson;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();

//        productsBetweenPriceWithoutBuyer();

//        getUsersWithSoldProducts();

//        getCategoryStats();

//        this.userService.getUsersWithSoldProductsOrderByCount();

        xmlMarshallDemo();
    }

    /**
     * [
     *   {
     *     "category": "Drugs",
     *     "productCount": 68,
     *     "averagePrice": 836.952941,
     *     "totalRevenue": 56912.80
     *   },
     *   ...
     *   ]
     *
     *   <categories-stats>
     *       <category>
     *           <name>Drugs</name>
     *           <productCount>68</productCount>
     *           <averagePrice>836..</averagePrice>
     *           <totalRevenue>56912.80</totalRevenue>
     *       </category>
     *       ...
     *   <categories-stats/>
     */
    private void xmlDemo() throws JAXBException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "      <category>\n" +
                "         <name>Drugs</name>\n" +
                "         <product-count>68</product-count>\n" +
                "         <averagePrice>836.952941</averagePrice>\n" +
                "         <totalRevenue>56912.80</totalRevenue>\n" +
                "      </category>";

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        XMLCategoryStatsDTO result = (XMLCategoryStatsDTO)
                unmarshaller.unmarshal(inputStream);

        System.out.println(result);
    }

    private void xmlMarshallDemo() throws JAXBException, IOException {
        List<XMLCategoryStatsDTO> xmlResult =
            this.productsService
                .getCategoryStatistics()
                .stream()
                .map(XMLCategoryStatsDTO::new)
                .collect(Collectors.toList());

        XMLCategoryStatsList xmlCategoryStatsList =
                new XMLCategoryStatsList(xmlResult);

        JAXBContext context = JAXBContext.newInstance(XMLCategoryStatsList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(xmlCategoryStatsList, System.out);


        File writer =
            new File("/tmp/stats.xml");

        writer.createNewFile();
        marshaller.marshal(xmlCategoryStatsList, writer);
    }


    private void getCategoryStats() {
        List<CategoryStatsDTO> result = this.productsService.getCategoryStatistics();

        String json = this.gson.toJson(result);
        System.out.println(json);
    }

    private void getUsersWithSoldProducts() {
        List<UserWithSoldProductsDTO> usersWithSoldProducts = this.userService.getUsersWithSoldProducts();

        String json = this.gson.toJson(usersWithSoldProducts);

        System.out.println(json);
    }

    private void productsBetweenPriceWithoutBuyer() {
        List<ProductWithoutBuyerDTO> productsForSell = this.productsService.getProductsInPriceRangeForSell(500, 1000);

        String json = this.gson.toJson(productsForSell);

        System.out.println(json);
    }
}
