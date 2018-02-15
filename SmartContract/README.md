# Token and Smart Contract implementation in solidity

This is a part of a project done for the
[Blockchain and Iot course](http://biots.org/)
at ETH. During the project, a significantly more sophisticated token and smart contract system was developed. This is a programm which works and implements some fundamental and more advanced ideas.

It includes an implementation of an ERC223 compatible token and a standard smart contract build upon that.

Some of the properties of the smart contract are:
* Each user gets a fitness score on a regular basis (around once a week)
  * Depending on the fitness score, they get some tokens according to a formula
* They can spend these tokens on betting
  * each week, one user, who bet the most, wins
  * 10% of the unused tokens of participants is lost every week to make it more interesting

We have two implementations for the smart contract: SmartContract.sol works on some predetermined data, SmartContractOraclize.sol also accesses external random data with Oraclize. The other files are the implementations of the ERC223 token.

 
