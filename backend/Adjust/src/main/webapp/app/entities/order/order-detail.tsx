import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './order.reducer';
import { IOrder } from 'app/shared/model/order.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrderDetail = (props: IOrderDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { orderEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.order.detail.title">Order</Translate> [<b>{orderEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="username">
              <Translate contentKey="adjustApp.order.username">Username</Translate>
            </span>
          </dt>
          <dd>{orderEntity.username}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="adjustApp.order.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{orderEntity.firstName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="adjustApp.order.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{orderEntity.lastName}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="adjustApp.order.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{orderEntity.phoneNumber}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="adjustApp.order.email">Email</Translate>
            </span>
          </dt>
          <dd>{orderEntity.email}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="adjustApp.order.country">Country</Translate>
            </span>
          </dt>
          <dd>{orderEntity.country}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="adjustApp.order.state">State</Translate>
            </span>
          </dt>
          <dd>{orderEntity.state}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="adjustApp.order.city">City</Translate>
            </span>
          </dt>
          <dd>{orderEntity.city}</dd>
          <dt>
            <span id="address1">
              <Translate contentKey="adjustApp.order.address1">Address 1</Translate>
            </span>
          </dt>
          <dd>{orderEntity.address1}</dd>
          <dt>
            <span id="address2">
              <Translate contentKey="adjustApp.order.address2">Address 2</Translate>
            </span>
          </dt>
          <dd>{orderEntity.address2}</dd>
          <dt>
            <span id="done">
              <Translate contentKey="adjustApp.order.done">Done</Translate>
            </span>
          </dt>
          <dd>{orderEntity.done ? 'true' : 'false'}</dd>
          <dt>
            <span id="received">
              <Translate contentKey="adjustApp.order.received">Received</Translate>
            </span>
          </dt>
          <dd>{orderEntity.received ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="adjustApp.order.cart">Cart</Translate>
          </dt>
          <dd>{orderEntity.cartId ? orderEntity.cartId : ''}</dd>
        </dl>
        <Button tag={Link} to="/order" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order/${orderEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ order }: IRootState) => ({
  orderEntity: order.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrderDetail);
