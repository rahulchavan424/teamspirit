// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

//contract that inherits both ERC20 and ERC721
contract ERC20AndERC721 is ERC20, ERC721, Ownable {
    //mapping from token IDs to ERC20 or ERC721
    mapping(uint256 => bool) public isERC721;

    //define a counter for assigning token IDs
    uint256 private _counter;

    constructor(uint256 initialSupply) ERC20("Gold", "GLD") onlyOwner {
        _mint(msg.sender, initialSupply);
    }

    //_mint function to set the isERC721 mapping
    function _mint(address to, uint256 tokenId) internal {
        ERC721._mint(to, tokenId);
        isERC721[tokenId] = true;
    }

    //function for minting ERC20 tokens
    function mintERC20(address to) public {
        uint256 tokenId = ++_counter;
        _mint(to, tokenId);
        isERC721[tokenId] = false;
    }

    //function for minting ERC721 tokens
    function mintERC721(address to) public {
        uint256 tokenId = ++_counter;
        _mint(to, tokenId);
        isERC721[tokenId] = true;
    }

}
