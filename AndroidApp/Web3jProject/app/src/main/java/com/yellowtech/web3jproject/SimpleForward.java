//Old version of the contract, is not used anymore

package com.yellowtech.web3jproject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class SimpleForward extends Contract {
    private static final String BINARY = "606060405234156200001057600080fd5b60408051908101604052600c81527f466f7277617264546f6b656e000000000000000000000000000000000000000060208201526002908051620000599291602001906200010e565b506040805190810160405260038082527f46574400000000000000000000000000000000000000000000000000000000006020830152908051620000a29291602001906200010e565b506004805460ff1916601217905569d3c21bcecceda1000000600581905560068054600160a060020a03338116600160a060020a0319928316811793849055921660009081526001602052604081209390935560078054909116909117905560085542600955620001b3565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200015157805160ff191683800117855562000181565b8280016001018555821562000181579182015b828111156200018157825182559160200191906001019062000164565b506200018f92915062000193565b5090565b620001b091905b808211156200018f57600081556001016200019a565b90565b610f3880620001c36000396000f3006060604052600436106100cc5763ffffffff60e060020a60003504166306fdde0381146100d157806309e69ede1461015b57806318160ddd146101ac578063313ce567146101d157806333a581d2146101fa57806333ce85e41461020d5780633847cb591461022e57806368ffcaf41461025d5780636ac7db051461027057806370a082311461028f5780638da5cb5b146102ae57806395d89b41146102c15780639b25cacb146102d4578063a9059cbb146102ea578063be45fd6214610320578063f6368f8a14610385575b600080fd5b34156100dc57600080fd5b6100e461042c565b60405160208082528190810183818151815260200191508051906020019080838360005b83811015610120578082015183820152602001610108565b50505050905090810190601f16801561014d5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561016657600080fd5b61017a600160a060020a03600435166104d4565b604051808681526020018581526020018481526020018381526020018281526020019550505050505060405180910390f35b34156101b757600080fd5b6101bf610503565b60405190815260200160405180910390f35b34156101dc57600080fd5b6101e4610509565b60405160ff909116815260200160405180910390f35b341561020557600080fd5b6101bf610512565b341561021857600080fd5b61022c600160a060020a0360043516610518565b005b341561023957600080fd5b6102416105b1565b604051600160a060020a03909116815260200160405180910390f35b341561026857600080fd5b61022c6105c0565b341561027b57600080fd5b61022c600160a060020a03600435166107b2565b341561029a57600080fd5b6101bf600160a060020a036004351661083b565b34156102b957600080fd5b610241610856565b34156102cc57600080fd5b6100e4610865565b34156102df57600080fd5b6102416004356108d8565b34156102f557600080fd5b61030c600160a060020a03600435166024356108f3565b604051901515815260200160405180910390f35b341561032b57600080fd5b61030c60048035600160a060020a03169060248035919060649060443590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061092f95505050505050565b341561039057600080fd5b61030c60048035600160a060020a03169060248035919060649060443590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052818152929190602084018383808284375094965061096395505050505050565b610434610efa565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104ca5780601f1061049f576101008083540402835291602001916104ca565b820191906000526020600020905b8154815290600101906020018083116104ad57829003601f168201915b5050505050905090565b600a60205260009081526040902080546001820154600283015460038401546004909401549293919290919085565b60055490565b60045460ff1690565b60001981565b600160a060020a0381166000908152600a60205260409020600181018054600283018190556103ef908101909155600390910180544291829055603c91030490811515610566576001909101905b600160a060020a0383166000908152600a60205260409020600401829055818181151561058f57fe5b600160a060020a039094166000908152600a6020526040902093049092555050565b600754600160a060020a031681565b60075460009033600160a060020a039081169116146105de57600080fd5b5060005b6008548110156107ab576000818152600b602052604090205461060d90600160a060020a0316610518565b6000818152600b6020908152604080832054600160a060020a03168352600a9091529020546103e89011156106ee576000818152600b6020908152604080832054600160a060020a0316808452600a90925291829020600481015490546106e8936103e86103e719909201909202029080519081016040908152600182527f780000000000000000000000000000000000000000000000000000000000000060208301528051908101604052600181527f30000000000000000000000000000000000000000000000000000000000000006020820152610963565b506107a3565b6007546000828152600b6020908152604080832054600160a060020a039081168452600a90925291829020600481015490546107a19492909216926103e89283039091029091029080519081016040908152600182527f780000000000000000000000000000000000000000000000000000000000000060208301528051908101604052600181527f30000000000000000000000000000000000000000000000000000000000000006020820152610963565b505b6001016105e2565b5042600955565b60075433600160a060020a039081169116146107cd57600080fd5b6008805460018082019092556000908152600b60209081526040808320805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a038716908117909155835292905220670de0b6b3a76400009081905560058054909101905561083881610518565b50565b600160a060020a031660009081526001602052604090205490565b600654600160a060020a031681565b61086d610efa565b60038054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104ca5780601f1061049f576101008083540402835291602001916104ca565b600b60205260009081526040902054600160a060020a031681565b60006108fd610efa565b61090684610bb6565b1561091d57610916848483610bbe565b9150610928565b610916848483610daf565b5092915050565b600061093a84610bb6565b156109515761094a848484610bbe565b905061095c565b61094a848484610daf565b9392505050565b600061096e85610bb6565b15610ba0578361097d3361083b565b101561098857600080fd5b61099a6109943361083b565b85610ecd565b600160a060020a0333166000908152600160205260409020556109c56109bf8661083b565b85610ee2565b600160a060020a0386166000818152600160205260408082209390935590918490518082805190602001908083835b60208310610a135780518252601f1990920191602091820191016109f4565b6001836020036101000a0380198251168184511617909252505050919091019250604091505051809103902060e060020a9004903387876040518563ffffffff1660e060020a0281526004018084600160a060020a0316600160a060020a03168152602001838152602001828051906020019080838360005b83811015610aa4578082015183820152602001610a8c565b50505050905090810190601f168015610ad15780820380516001836020036101000a031916815260200191505b50935050505060006040518083038185886187965a03f193505050501515610af557fe5b826040518082805190602001908083835b60208310610b255780518252601f199092019160209182019101610b06565b6001836020036101000a0380198251168184511617909252505050919091019250604091505051809103902085600160a060020a031633600160a060020a03167fe19260aff97b920c7df27010903aeb9c8d2be5d310a2c67824cf3f15396e4c168760405190815260200160405180910390a4506001610bae565b610bab858585610daf565b90505b949350505050565b6000903b1190565b60008083610bcb3361083b565b1015610bd657600080fd5b610be26109943361083b565b600160a060020a033316600090815260016020526040902055610c076109bf8661083b565b600160a060020a03861660008181526001602052604090819020929092558692509063c0ee0b8a90339087908790518463ffffffff1660e060020a0281526004018084600160a060020a0316600160a060020a0316815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610ca0578082015183820152602001610c88565b50505050905090810190601f168015610ccd5780820380516001836020036101000a031916815260200191505b50945050505050600060405180830381600087803b1515610ced57600080fd5b6102c65a03f11515610cfe57600080fd5b505050826040518082805190602001908083835b60208310610d315780518252601f199092019160209182019101610d12565b6001836020036101000a0380198251168184511617909252505050919091019250604091505051809103902085600160a060020a031633600160a060020a03167fe19260aff97b920c7df27010903aeb9c8d2be5d310a2c67824cf3f15396e4c168760405190815260200160405180910390a4506001949350505050565b600082610dbb3361083b565b1015610dc657600080fd5b610dd8610dd23361083b565b84610ecd565b600160a060020a033316600090815260016020526040902055610e03610dfd8561083b565b84610ee2565b600160a060020a03851660009081526001602052604090819020919091558290518082805190602001908083835b60208310610e505780518252601f199092019160209182019101610e31565b6001836020036101000a0380198251168184511617909252505050919091019250604091505051809103902084600160a060020a031633600160a060020a03167fe19260aff97b920c7df27010903aeb9c8d2be5d310a2c67824cf3f15396e4c168660405190815260200160405180910390a45060019392505050565b600081831015610edc57600080fd5b50900390565b60008160001903831115610ef557600080fd5b500190565b602060405190810160405260008152905600a165627a7a72305820eba3d610f72d7c5ac7c60085a563f9180890861ce3651d68fbf86c2e905393510029";

    protected SimpleForward(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleForward(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<DynamicBytes>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.data = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<DynamicBytes>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.data = (byte[]) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> name() {
        Function function = new Function("name",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> participants(String param0) {
        final Function function = new Function("participants",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> totalSupply() {
        Function function = new Function("totalSupply",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> decimals() {
        Function function = new Function("decimals",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> MAX_UINT256() {
        Function function = new Function("MAX_UINT256",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> collectData(String participant) {
        Function function = new Function(
                "collectData",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(participant)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> administration() {
        Function function = new Function("administration",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> testFitnessScore() {
        Function function = new Function(
                "testFitnessScore",
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> createNewParticipant(String participant) {
        Function function = new Function(
                "createNewParticipant",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(participant)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        Function function = new Function("balanceOf",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> symbol() {
        Function function = new Function("symbol",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> participantsIndex(BigInteger param0) {
        Function function = new Function("participantsIndex",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        Function function = new Function(
                "transfer",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to),
                        new org.web3j.abi.datatypes.generated.Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value, byte[] _data) {
        Function function = new Function(
                "transfer",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to),
                        new org.web3j.abi.datatypes.generated.Uint256(_value),
                        new org.web3j.abi.datatypes.DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value, byte[] _data, String _custom_fallback) {
        Function function = new Function(
                "transfer",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to),
                        new org.web3j.abi.datatypes.generated.Uint256(_value),
                        new org.web3j.abi.datatypes.DynamicBytes(_data),
                        new org.web3j.abi.datatypes.Utf8String(_custom_fallback)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<SimpleForward> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleForward.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SimpleForward> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleForward.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static SimpleForward load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleForward(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SimpleForward load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleForward(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class TransferEventResponse {
        public String from;

        public String to;

        public byte[] data;

        public BigInteger value;
    }
}
