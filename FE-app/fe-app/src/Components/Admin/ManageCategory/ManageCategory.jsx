import React, { useState, useEffect, Fragment } from "react";

import { FilterMatchMode } from "primereact/api";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { InputText } from "primereact/inputtext";
import { Dropdown } from "primereact/dropdown";

import axios from "axios";

import ToolbarCategory from "./ToolbarCategory";

// Component
export default function ManageCategory() {
  // Hooks
  const [customers2, setCustomers2] = useState(null);

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
  const [selectedProduct1, setSelectedProduct1] = useState(null);
  const [force, setForce] = useState(0);

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
    "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdXN0b21lckBnbWFpbC5jb20iLCJpYXQiOjE2NTc2OTUyNDEsImV4cCI6MTY1Nzc4MTY0MX0.obOyBN1OL-V_ZahY3ly0NFda3jykvXrWOkg3thzmm_StlWeKWxlAl_307MuCLdQh3qmc6t-2aPYbMr218FqGtg";
    axios
      .get(`http://localhost:8088/category/`, {
        headers: {
          Authorization: "Bearer " + token, //the token is a variable which holds the token
        },
      })
      .then((res) => {
        setCustomers2(res.data.data);

        setLoading2(false);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [force]);

  // Render
  return (
    <Fragment>
      <ToolbarCategory
        selectedProduct1={selectedProduct1}
        setSelectedProduct1={setSelectedProduct1}
        setForce={() => {
          setForce(force + 1);
          setLoading2(true);
        }}
      />

      <div className="datatable-filter-demo">
        <div className="card">
          <DataTable
            value={customers2}
            paginator
            className="p-datatable-customers"
            rows={10}
            dataKey="idCategory"
            filters={filters2}
            filterDisplay="row"
            loading={loading2}
            responsiveLayout="scroll"
            globalFilterFields={["nameCategory,"]}
            selectionMode="single"
            selection={selectedProduct1}
            onSelectionChange={(e) => setSelectedProduct1(e.value)}
            header={header2}
            emptyMessage="No products found."
          >
            <Column
              field="nameCategory"
              header="Name Category"
          
           
            />           
          </DataTable>
        </div>
      </div>
    </Fragment>
  );
}
