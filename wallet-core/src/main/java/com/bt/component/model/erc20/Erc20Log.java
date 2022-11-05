package com.bt.component.model.erc20;

import java.math.BigInteger;

public class Erc20Log {

	/**Erc20合约转出地址*/
	private String address;
	
	/**Erc20转出金额*/
	private BigInteger amount;
	
	/**log序号*/
	private Integer logIndex;
	
	/**合约地址(例如我解析一个批量发送代币的合约，这个合约的地址其实并不是erc20合约，真实的代币合约地址在log中)*/
	private String contractAddress;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public Integer getLogIndex() {
		return logIndex;
	}

	public void setLogIndex(Integer logIndex) {
		this.logIndex = logIndex;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	
}
