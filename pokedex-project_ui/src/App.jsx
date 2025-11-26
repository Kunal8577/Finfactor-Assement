import React from 'react'
import './App.css'
import PokemonSearch from './components/PokemonSearch'

export default function App() {
  return (
    <div className="app">
      <header><h1>Pokedex — Search</h1></header>
      <main><PokemonSearch/></main>
    </div>
  )
}

