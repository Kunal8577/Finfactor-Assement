import React, { useState } from 'react'
import PokemonCard from './PokemonCard'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080/api'

export default function PokemonSearch(){
  const [query, setQuery] = useState('')
  const [pokemon, setPokemon] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  async function search(e){
    e.preventDefault()
    if(!query) return
    setLoading(true); setError(null); setPokemon(null)

    try{
      const res = await fetch(`${API_BASE}/pokemon/${encodeURIComponent(query)}`)
      if(!res.ok){
        const body = await res.json().catch(()=>({message:'error'}))
        throw new Error(body.message || 'Error fetching pokemon')
      }
      const data = await res.json()
      setPokemon(data)
    } catch(err) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="search-box">
      <form onSubmit={search}>
        <input value={query} onChange={e=>setQuery(e.target.value)} placeholder="Enter pokemon name e.g. pikachu" />
        <button type="submit">Search</button>
      </form>

      {loading && <div className="loading">Loading...</div>}
      {error && <div className="error">{error}</div>}
      {pokemon && <PokemonCard pokemon={pokemon} />}
    </div>
  )
}
