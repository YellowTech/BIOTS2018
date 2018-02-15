pragma solidity ^0.4.17;


contract Token {
    mapping (uint256 => uint256) public balances;
    uint256 public availableId;
    
    function Token() public {
        availableId = 0;
    }
    
    function register() public  {
        availableId += 1;
    }
    
    function newId() public view returns (uint256) {
        return availableId;
    }
    
    function getBalance(uint256 id) public view returns (uint256) {
        return balances[id];
    }
    
    function getTokens(uint256 id) payable public {
        balances[id] += uint(block.blockhash(block.number-1))%10 + 1;
    }
    
    function sendTokens(uint256 id, uint256 destinationId, uint256 value) public {
        require(value<=balances[id]);
        balances[id] -= value;
        balances[destinationId] += value;
    }
}


//More complicated code which should be working but I had weird problems so I built a simpler version
/*//import "github.com/oraclize/ethereum-api/oraclizeAPI.sol";



contract Token is usingOraclize {
    mapping (uint256 => uint256) public balances;
    mapping (bytes32 => uint256) myidToUserId;
    uint256 public availableId;
    string public log1;
    string public log2;
    
    function Token() public {
        availableId=0;
    }
    
    function register() public returns (uint256) {
        uint256 ret = availableId;
        availableId += 1;
        return ret;
    }
    
    function getBalance(uint256 id) public view returns (uint256) {
        return balances[id];
    }
    
    function getTokens(uint256 id) payable public {
        
        if (oraclize_getPrice("URL") > this.balance) {
            log1="Oraclize query was NOT sent, please add some ETH to cover for the query fee";
        } else {
            log1="Oraclize query was sent, standing by for the answer..";
            bytes32 callid = oraclize_query("URL","json(https://biots.yellowtech.ch/?min=1&max=50).R");
            myidToUserId[callid] = id;
        }
    }
    
    function __callback(bytes32 _myid, string _result) {
        require (msg.sender == oraclize_cbAddress());
        log2=_result;
        balances[myidToUserId[_myid]] += parseInt(_result, 0);
        
    }
    
    function sendTokens(uint256 id, uint256 destinationId, uint256 value) public {
        require(value<=balances[id]);
        balances[id]-=value;
        balances[destinationId]+=value;
    }
}*/