import React, { useState, useEffect, Fragment } from "react";

import { FilterMatchMode } from "primereact/api";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { InputText } from "primereact/inputtext";
import { Dropdown } from "primereact/dropdown";

import axios from "axios";

import ToolbarProduct from "./ToolbarProduct";

// Component
export default function ManageProduct() {
  // Hooks
  const [customers2, setCustomers2] = useState(null);
  const [selectedProduct1, setSelectedProduct1] = useState(null);

  const [filters2, setFilters2] = useState({
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
    descripton: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
    name: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
    price: { value: null, matchMode: FilterMatchMode.EQUALS },
    status: { value: null, matchMode: FilterMatchMode.EQUALS },
    quantity: { value: null, matchMode: FilterMatchMode.EQUALS },
  });

  const [loading2, setLoading2] = useState(true);
  const [globalFilterValue2, setGlobalFilterValue2] = useState("");
  const [force, setforce] = useState(0);

  // Handles
  const onGlobalFilterChange2 = (e) => {
    const value = e.target.value;
    let _filters2 = { ...filters2 };
    _filters2["global"].value = value;

    setFilters2(_filters2);
    setGlobalFilterValue2(value);
  };

  // Sub component
  const renderHeader2 = () => {
    return (
      <div className="flex justify-content-end">
        <span className="p-input-icon-left">
          <i className="pi pi-search" />
          <InputText
            value={globalFilterValue2}
            onChange={onGlobalFilterChange2}
            placeholder="Keyword Search"
          />
        </span>
      </div>
    );
  };

  const statusBodyTemplate = (rowData) => {
    return (
      <span className={`customer-badge status-${rowData.status}`}>
        {rowData.status}
      </span>
    );
  };

  const statusRowFilterTemplate = (options) => {
    return (
      <Dropdown
        value={options.value}
        options={statuses}
        onChange={(e) => options.filterApplyCallback(e.value)}
        itemTemplate={statusItemTemplate}
        placeholder="Select a Status"
        className="p-column-filter"
        showClear
      />
    );
  };

  const statusItemTemplate = (option) => {
    return <span className={`customer-badge status-${option}`}>{option}</span>;
  };

  // Variables
  const header2 = renderHeader2();

  const statuses = ["Active", "Unactive"];

  // Call API
  useEffect(() => {
    let token =
      "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lckBnbWFpbC5jb20iLCJpYXQiOjE2NTc2ODg3MzEsImV4cCI6MTY1Nzc3NTEzMX0.wSwjCTppUiTzin1keKMFWx1rfmtHcm1CAhfkZTSS0SJOS7MPzOFL_Uvw2PSFVMUVx2PaMJ4VETWH5knSWPgjYQ";
    axios
      .get(`http://localhost:8088/products/`, {
        headers: {
          Authorization: "Bearer " + token, //the token is a variable which holds the token
        },
      })
      .then((res) => {
        console.log(res.data);

        setCustomers2(res.data);
        setLoading2(false);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [force]);

  // Render
  return (
    <Fragment>
      <ToolbarProduct
        setForce={() => setforce(force + 1)}
        selectedProduct1={selectedProduct1}
        setSelectedProduct1={setSelectedProduct1}
      />

      <div className="datatable-filter-demo">
        <div className="card">
          <DataTable
            value={customers2}
            paginator
            className="p-datatable-customers"
            rows={10}
            dataKey="idProduct"
            filters={filters2}
            filterDisplay="row"
            loading={loading2}
            responsiveLayout="scroll"
            selectionMode="single"
            selection={selectedProduct1}
            onSelectionChange={(e) => setSelectedProduct1(e.value)}
            globalFilterFields={[
              "productName",         
              "category",
            ]}
            header={header2}
            emptyMessage="No products found."
          >
            <Column
              field="productName"
              header="Name Product"
              filter
              filterPlaceholder="Search by name"
              style={{ minWidth: "12rem" }}
            />
            <Column
              header="Create_Date"
              field="createDate"
              style={{ minWidth: "12rem" }}
            />
            <Column
              header="Update_Date"
              field="updateDate"
              style={{ minWidth: "12rem" }}
            />
            <Column
              header="Quantity"
              field="quantity"
              showFilterMenu={false}
              filterMenuStyle={{ width: "14rem" }}
              style={{ minWidth: "14rem" }}
            />
            <Column
              header="Price"
              field="price"
              showFilterMenu={false}
              filterMenuStyle={{ width: "14rem" }}
              style={{ minWidth: "14rem" }}
            />
            <Column
              field="image"
              header="image"
              showFilterMenu={false}
              filterMenuStyle={{ width: "14rem" }}
              style={{ minWidth: "12rem" }}
          
            />
            <Column
            field="category.nameCategory"
            header="category"
            showFilterMenu={false}
            filterMenuStyle={{ width: "14rem" }}
            style={{ minWidth: "12rem" }}
        
          />
          </DataTable>
        </div>
      </div>
    </Fragment>
  );
}
