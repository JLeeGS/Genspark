import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {App, Footer} from './App';
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
  <App />,
  document.getElementById('HeaderComponent'),
);
ReactDOM.render(
  <Footer />,
  document.getElementById('contact'),
);



reportWebVitals();