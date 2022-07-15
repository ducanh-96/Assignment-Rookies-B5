import { Fragment, useState, useEffect } from "react";

import { InputNumber } from "primereact/inputnumber";

import { InputText } from "primereact/inputtext";
import { classNames } from "primereact/utils";
import { Dropdown } from "primereact/dropdown";
import { Calendar } from 'primereact/calendar';



import axios from "axios";

export default function DialogUpdateProduct(props) {
  // Variables
  const setProduct = props.setProduct;
  const product = props.product;
  const submitted = props.submitted;

  const statuses = [
    {
      label: "Active",
      value: "Active",
    },
    {
      label: "Unactive",
      value: "Unactive",
    },
  ];

  // Hooks
  const [categoryItems, setCategoryItems] = useState([]);

  // Handles
  const onInputChange = (e, name) => {
    const val = (e.target && e.target.value) || "";
    let _product = { ...props.product };
    _product[`${name}`] = val;

    console.log(_product);
    setProduct(_product);
  };

  // Call api
  useEffect(() => {
    let token =
      "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lckBnbWFpbC5jb20iLCJpYXQiOjE2NTc2OTQ5MzksImV4cCI6MTY1Nzc4MTMzOX0.qT1Trvy_vWAmNTqNML6HFlj5UdvShZjZM101MY_68PbtbtckLHHEzky4yzF3_r4QIt-H7rwU3qXXayT82ZhQ7A";
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
        <label htmlFor="productName">Name</label>
        <InputText
          id="productName"
//get Data
          value={product.productName}
          onChange={(e) => onInputChange(e, "productName")}
          required
          autoFocus
          className={classNames({ "p-invalid": submitted && !product.name })}
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
//get Data
          value={product.updateDate}
          onChange={(e) => onInputChange(e, "updateDate")}
          required
          autoFocus
          className={classNames({ "p-invalid": submitted && !product.name })}
        />
        {submitted && !product.updateDate && (
          <small className="p-error">Name is required.</small>
        )}
      </div>

      <div className="formgrid grid">
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
      </div>

      <div className="field">
        <label htmlFor="image">image</label>
        <InputText
          id="image"
//get Data
          value={product.image}
          onChange={(e) => onInputChange(e, "image")}
          required
          autoFocus
          className={classNames({ "p-invalid": submitted && !product.image })}
        />
        {submitted && !product.image && (
          <small className="p-error">Name is required.</small>
        )}
      </div>

      <div className="field">
        <label className="mb-3">Category</label>
        <Dropdown
          value={product.category.idCategory}
          options={categoryItems}
          optionLabel="nameCategory"
          optionValue="idCategory"
          onChange={(e) => onInputChange(e, "category.idCategory")}
          placeholder="Select a Category"
        />
      </div>
    </Fragment>
  );
}
