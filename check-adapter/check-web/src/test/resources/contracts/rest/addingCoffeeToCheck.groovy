package contracts.rest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'should add latte to check'
    request {
        method('PUT')
        headers {
            contentType(applicationJson())
        }
        url '/checks/00570c06-38a0-43a1-a690-d3792fe83b36/coffees/b7a8d2e4-99ea-4849-9347-bfd76d5e94d5'
        body([
                checkID : '00570c06-38a0-43a1-a690-d3792fe83b36',
                coffeeID: 'b7a8d2e4-99ea-4849-9347-bfd76d5e94d5',
                quantity: 1
        ])
    }
    response {
        status(201)
        headers {
            header(location(), 'http://check-service/checks/00570c06-38a0-43a1-a690-d3792fe83b36/coffees/b7a8d2e4-99ea-4849-9347-bfd76d5e94d5')
        }
    }
}