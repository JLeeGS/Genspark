import React from 'react';
import './App.css';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent'

function App() {
  return (
    <div>

    </div>
  );
}

function Header() {
  return (
    <div>
      <div id="HeaderComponent">
        <HeaderComponent /> 
      </div>
    </div>
  );
}

function Footer() {
  return (
      <div id="contact">
        <FooterComponent />
      </div>
  );
}


export {Header, Footer}


