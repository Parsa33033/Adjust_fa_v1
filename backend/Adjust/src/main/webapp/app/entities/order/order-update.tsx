import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICart } from 'app/shared/model/cart.model';
import { getEntities as getCarts } from 'app/entities/cart/cart.reducer';
import { getEntity, updateEntity, createEntity, reset } from './order.reducer';
import { IOrder } from 'app/shared/model/order.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrderUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrderUpdate = (props: IOrderUpdateProps) => {
  const [cartId, setCartId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { orderEntity, carts, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/order');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCarts();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...orderEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.order.home.createOrEditLabel">
            <Translate contentKey="adjustApp.order.home.createOrEditLabel">Create or edit a Order</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : orderEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="order-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="order-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="usernameLabel" for="order-username">
                  <Translate contentKey="adjustApp.order.username">Username</Translate>
                </Label>
                <AvField id="order-username" type="text" name="username" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameLabel" for="order-firstName">
                  <Translate contentKey="adjustApp.order.firstName">First Name</Translate>
                </Label>
                <AvField id="order-firstName" type="text" name="firstName" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameLabel" for="order-lastName">
                  <Translate contentKey="adjustApp.order.lastName">Last Name</Translate>
                </Label>
                <AvField id="order-lastName" type="text" name="lastName" />
              </AvGroup>
              <AvGroup>
                <Label id="phoneNumberLabel" for="order-phoneNumber">
                  <Translate contentKey="adjustApp.order.phoneNumber">Phone Number</Translate>
                </Label>
                <AvField id="order-phoneNumber" type="text" name="phoneNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="order-email">
                  <Translate contentKey="adjustApp.order.email">Email</Translate>
                </Label>
                <AvField id="order-email" type="text" name="email" />
              </AvGroup>
              <AvGroup>
                <Label id="countryLabel" for="order-country">
                  <Translate contentKey="adjustApp.order.country">Country</Translate>
                </Label>
                <AvField id="order-country" type="text" name="country" />
              </AvGroup>
              <AvGroup>
                <Label id="stateLabel" for="order-state">
                  <Translate contentKey="adjustApp.order.state">State</Translate>
                </Label>
                <AvField id="order-state" type="text" name="state" />
              </AvGroup>
              <AvGroup>
                <Label id="cityLabel" for="order-city">
                  <Translate contentKey="adjustApp.order.city">City</Translate>
                </Label>
                <AvField id="order-city" type="text" name="city" />
              </AvGroup>
              <AvGroup>
                <Label id="address1Label" for="order-address1">
                  <Translate contentKey="adjustApp.order.address1">Address 1</Translate>
                </Label>
                <AvField id="order-address1" type="text" name="address1" />
              </AvGroup>
              <AvGroup>
                <Label id="address2Label" for="order-address2">
                  <Translate contentKey="adjustApp.order.address2">Address 2</Translate>
                </Label>
                <AvField id="order-address2" type="text" name="address2" />
              </AvGroup>
              <AvGroup check>
                <Label id="doneLabel">
                  <AvInput id="order-done" type="checkbox" className="form-check-input" name="done" />
                  <Translate contentKey="adjustApp.order.done">Done</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="receivedLabel">
                  <AvInput id="order-received" type="checkbox" className="form-check-input" name="received" />
                  <Translate contentKey="adjustApp.order.received">Received</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="order-cart">
                  <Translate contentKey="adjustApp.order.cart">Cart</Translate>
                </Label>
                <AvInput id="order-cart" type="select" className="form-control" name="cartId">
                  <option value="" key="0" />
                  {carts
                    ? carts.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/order" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  carts: storeState.cart.entities,
  orderEntity: storeState.order.entity,
  loading: storeState.order.loading,
  updating: storeState.order.updating,
  updateSuccess: storeState.order.updateSuccess,
});

const mapDispatchToProps = {
  getCarts,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrderUpdate);
