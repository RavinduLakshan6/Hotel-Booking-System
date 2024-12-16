import React from 'react'
import CategoryData from './CategoryData';
import './CategoryStyle.css';
import cat1 from '../../Assets/bg1.jpg';
import cat2 from '../../Assets/bg2.jpg';
import cat3 from '../../Assets/bg3.jpg';

export default function Category() {
  return (
    <div className="category">

    <h1>Categories</h1>
    <p>You can discover unique room categories</p>

    <div className="cat-card">
        <CategoryData
        image = {cat1}
        heading = "Single Room"
        text = "sample text 01"
        />

        <CategoryData
        image = {cat2}
        heading = "Double Room"
        text = "sample text 02"
        />

        <CategoryData
        image = {cat3}
        heading = "Family Room"
        text = "sample text 03"
        />

    </div>

    </div>
  )
}
