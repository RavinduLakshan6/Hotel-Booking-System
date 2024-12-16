import React from 'react'
import Category from './Category';
import './CategoryStyle.css';


export default function CategoryData(props) {
  return (
    <div className="cat-block">
        <div className="cat-image">
            <img src={props.image} alt="category-image" />
        </div>
        <h3> {props.heading} </h3>
        <p> {props.text} </p>
    </div>
  )
}
