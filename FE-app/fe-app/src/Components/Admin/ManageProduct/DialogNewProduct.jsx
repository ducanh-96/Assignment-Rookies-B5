import { Fragment, useState, useEffect } from "react";

import { InputText } from "primereact/inputtext";
import { Dropdown } from "primereact/dropdown";
import { FileUpload } from "primereact/fileupload";
import { InputNumber } from "primereact/inputnumber";
import { classNames } from "primereact/utils";
import { Calendar } from 'primereact/calendar';


import axios from "axios";

export default function DialogNewProduct(props) {
  // Variables
  const setProduct = props.setProduct;
  const product = props.product;
  const submitted = props.submitted;

  const [categoryItems, setCategoryItems] = useState([]);

  const statuses = [
    { label: "Active", value: "Active" },
    { label: "Unactive", value: "Unactive" },
  ];

  // Hooks

  // Handles
  const onInputChange = (e, name) => {
    const val = (e.target && e.target.value) || "";
    let _product = { ...props.product };
    _product[`${name}`] = val;

    console.log(_product);
    setProduct(_product);
  };

  // const myUploader = (e) => {
  //   console.log(e.files);
  // };

  // Call api
  useEffect(() => {
    let token =
      "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lckBnbWFpbC5jb20iLCJpYXQiOjE2NTc2ODg3MzEsImV4cCI6MTY1Nzc3NTEzMX0.wSwjCTppUiTzin1keKMFWx1rfmtHcm1CAhfkZTSS0SJOS7MPzOFL_Uvw2PSFVMUVx2PaMJ4VETWH5knSWPgjYQ";
    axios
      .get(`http://localhost:8088/category/`, {
        headers: {
          Authorization: "Bearer " + token, //the token is a variable which holds the token
        },
      })
      .then((res) => {
        console.log(res.data);

        setCategoryItems(res.data.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <Fragment>
      <div className="field">
        <label htmlFor="name">Name Product</label>
        <InputText
          id="productName"
          value={product.productName}
          onChange={(e) => onInputChange(e, "productName")}
          required
          autoFocus
          className={classNames({ "p-invalid": submitted && !product.productName })}
        />
        {submitted && !product.productName && (
          <small className="p-error">Name is required.</small>
        )}
      </div>
      <div className="field">
        <label htmlFor="createDate">createDate</label>
        <Calendar
          id="createDate"
          value={product.createDate}
          onChange={(e) => onInputChange(e, "createDate")}
          required
          rows={3}
          cols={20}
        />
      </div>
      <div className="field">
        <label htmlFor="updateDate">updateDate</label>
        <Calendar
          id="updateDate"
          value={product.descripton}
          onChange={(e) => onInputChange(e, "updateDate")}
          required
          rows={3}
          cols={20}
        />
      </div>
     
        <div className="field col">
          <label htmlFor="price">Price</label>
          <InputNumber
            id="price"
            value={product.price}
            onValueChange={(e) => onInputChange(e, "price")}
            mode="currency"
            currency="USD"
            locale="en-US"
            min={0}
          />
        </div>
        <div className="field col">
          <label htmlFor="quantity">Quantity</label>
          <InputNumber
            inputId="minmax-buttons"
            value={product.quantity}
            onValueChange={(e) => onInputChange(e, "quantity")}
            mode="decimal"
            showButtons
            min={0}
            max={100}
          />
        </div>
     

      <div className="field">
        <label className="mb-3">Category</label>
        <Dropdown
          value={product.category}
          options={categoryItems}
          optionLabel="nameCategory"
          // optionValue="idCategory"
          onChange={(e) => onInputChange(e, "category")}
          placeholder="Select a Category"
        />
      </div>

      <div className="field col">
        <label htmlFor="image" className="mb-3">
          Image
        </label>

        <FileUpload name="image" url="./upload" mode="basic" />
      </div>
    </Fragment>
  );
}
