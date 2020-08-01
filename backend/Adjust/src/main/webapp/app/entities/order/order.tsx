import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './order.reducer';
import { IOrder } from 'app/shared/model/order.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Order = (props: IOrderProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { orderList, match, loading } = props;
  return (
    <div>
      <h2 id="order-heading">
        <Translate contentKey="adjustApp.order.home.title">Orders</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.order.home.createLabel">Create new Order</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {orderList && orderList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.username">Username</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.firstName">First Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.lastName">Last Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.country">Country</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.state">State</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.city">City</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.address1">Address 1</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.address2">Address 2</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.done">Done</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.received">Received</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.order.cart">Cart</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderList.map((order, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${order.id}`} color="link" size="sm">
                      {order.id}
                    </Button>
                  </td>
                  <td>{order.username}</td>
                  <td>{order.firstName}</td>
                  <td>{order.lastName}</td>
                  <td>{order.phoneNumber}</td>
                  <td>{order.email}</td>
                  <td>{order.country}</td>
                  <td>{order.state}</td>
                  <td>{order.city}</td>
                  <td>{order.address1}</td>
                  <td>{order.address2}</td>
                  <td>{order.done ? 'true' : 'false'}</td>
                  <td>{order.received ? 'true' : 'false'}</td>
                  <td>{order.cartId ? <Link to={`cart/${order.cartId}`}>{order.cartId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${order.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${order.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${order.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.order.home.notFound">No Orders found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ order }: IRootState) => ({
  orderList: order.entities,
  loading: order.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Order);
