import { Fragment } from "react";

import { InputText } from "primereact/inputtext";
import { InputTextarea } from "primereact/inputtextarea";
import { classNames } from "primereact/utils";

import { Dropdown } from "primereact/dropdown";

export default function DialogNewCategory(props) {
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

  // Handles
  const onInputChange = (e, name) => {
    const val = (e.target && e.target.value) || "";
    let _product = { ...props.product };
    _product[`${name}`] = val;

    console.log(_product);
    setProduct(_product);
  };

  return (
    <Fragment>
      <div className="field">
        <label htmlFor="nameCategory">Name Category</label>
        <InputText
          id="nameCategory"
          value={product.nameCategory}
          onChange={(e) => onInputChange(e, "nameCategory")}
          required
          autoFocus
          className={classNames({ "p-invalid": submitted && !product.nameCategory })}
        />
        {submitted && !product.nameCategory && (
          <small className="p-error">Name is required.</small>
        )}
      
      </div>
     
     
    </Fragment>
  );
}
