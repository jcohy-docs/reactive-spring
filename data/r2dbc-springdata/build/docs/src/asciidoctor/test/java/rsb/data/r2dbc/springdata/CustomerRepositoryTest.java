package rsb.data.r2dbc.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import rsb.data.r2dbc.BaseCustomerRepositoryTest;
import rsb.data.r2dbc.SimpleCustomerRepository;

/**
 * 描述: .
 * <p>
 * Copyright © 2022 <a href="https://www.jcohy.com" target= "_blank">https://www.jcohy.com</a>
 * </p>
 *
 * @author jiac
 * @version 2022.04.0 2023/5/17:16:12
 * @since 2022.04.0
 */
public class CustomerRepositoryTest extends BaseCustomerRepositoryTest {
    @Autowired
    private SimpleCustomerRepository repository;
    @Override
    public SimpleCustomerRepository getRepository() {
        return this.repository;
    }
}
