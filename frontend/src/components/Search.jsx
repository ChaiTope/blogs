import React from 'react'
import { Col } from 'react-bootstrap'
import {RiSearch2Line} from 'react-icons/ri';

const Search = () => {
  return (
    <Col className='searchbox'>
        <RiSearch2Line className="searchicon"/>
        <input type="text" name='searchall' className='searchinput' placeholder='search'/>
    </Col>
  )
}

export default Search