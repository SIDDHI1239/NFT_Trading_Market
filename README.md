# NFT_Trading_Market
* Neelakanta Bhagavatula : ID# 015261909
* Rodrigo Colasso Teixeira : ID# 015954146
* Siddhi Chaturvedi : ID# 015258568
* Viswamithra Vallabhaneni : ID# 015570516

## Project Description

### Database Diagram

![CMPE275 - Term project](https://user-images.githubusercontent.com/62269628/203665715-0c8e832b-5af1-4b83-baa5-771d69af28e0.png)

### Use cases

- Sign up ( Local and Google )
- Sign in ( Local and Google )
- Account Verification 
- List cryptocurrency from the wallet
- Deposite cryptocurrency
- Withdraw cryptocurrency
- Create NFT
- List NFT from the wallet
- Sell NFT - Priced/Auction
- List available NTF to buy Priced/Auction ( with filter )
- Buy priced NTF
- Bid to an auction NFT
- Accept a bid
- Close auction


### DataBAse Entities Mapping
- User to Cryptocurrency_Wallet     : One to One 
- User to NFT                       : One to Many 
- User to sale                      : One to Many
- User to sale_Bid                  : One to Many
- Sale_bid to Sale                  : Many to Many
- NFT to Sale                       : One to Many
- User to Transaction               : One to Many
- NFT  to Transaction               : One to Many
- Cryptocurrency_Wallet to          : Many to One
  Cryptocurrency
- Transaction to Cryptocurrency     : Many to One
- Cryptocurrency to Sale            : One to Many
